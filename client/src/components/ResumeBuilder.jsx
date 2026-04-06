import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { apiRequest } from '../services';
import { toast, ToastContainer } from 'react-toast';
import { useNavigate } from 'react-router-dom';
import { FaUser, FaEnvelope, FaPhone, FaMapMarkerAlt, FaBriefcase, FaGraduationCap, FaCode, FaFileAlt } from 'react-icons/fa';
import Header from './Header';
import Footer from './Footer';

const ResumeBuilder = () => {
    const user = useSelector((state) => state.userDetail);
    const navigate = useNavigate();
    const [resumeData, setResumeData] = useState({
        personalInfo: {
            fullName: user?.name || '',
            email: user?.email || '',
            phone: '',
            location: '',
            linkedIn: '',
            portfolio: '',
            summary: ''
        },
        education: [
            {
                degree: '',
                institution: '',
                year: '',
                gpa: ''
            }
        ],
        experience: [
            {
                title: '',
                company: '',
                location: '',
                startDate: '',
                endDate: '',
                description: ''
            }
        ],
        skills: [],
        projects: [
            {
                name: '',
                description: '',
                technologies: '',
                link: ''
            }
        ]
    });

    const [customSkill, setCustomSkill] = useState('');

    const handlePersonalInfoChange = (e) => {
        const { name, value } = e.target;
        setResumeData(prev => ({
            ...prev,
            personalInfo: {
                ...prev.personalInfo,
                [name]: value
            }
        }));
    };

    const handleEducationChange = (index, e) => {
        const { name, value } = e.target;
        const updatedEducation = [...resumeData.education];
        updatedEducation[index] = {
            ...updatedEducation[index],
            [name]: value
        };
        setResumeData(prev => ({
            ...prev,
            education: updatedEducation
        }));
    };

    const addEducation = () => {
        setResumeData(prev => ({
            ...prev,
            education: [...prev.education, { degree: '', institution: '', year: '', gpa: '' }]
        }));
    };

    const removeEducation = (index) => {
        setResumeData(prev => ({
            ...prev,
            education: prev.education.filter((_, i) => i !== index)
        }));
    };

    const handleExperienceChange = (index, e) => {
        const { name, value } = e.target;
        const updatedExperience = [...resumeData.experience];
        updatedExperience[index] = {
            ...updatedExperience[index],
            [name]: value
        };
        setResumeData(prev => ({
            ...prev,
            experience: updatedExperience
        }));
    };

    const addExperience = () => {
        setResumeData(prev => ({
            ...prev,
            experience: [...prev.experience, { title: '', company: '', location: '', startDate: '', endDate: '', description: '' }]
        }));
    };

    const removeExperience = (index) => {
        setResumeData(prev => ({
            ...prev,
            experience: prev.experience.filter((_, i) => i !== index)
        }));
    };

    const handleProjectChange = (index, e) => {
        const { name, value } = e.target;
        const updatedProjects = [...resumeData.projects];
        updatedProjects[index] = {
            ...updatedProjects[index],
            [name]: value
        };
        setResumeData(prev => ({
            ...prev,
            projects: updatedProjects
        }));
    };

    const addProject = () => {
        setResumeData(prev => ({
            ...prev,
            projects: [...prev.projects, { name: '', description: '', technologies: '', link: '' }]
        }));
    };

    const removeProject = (index) => {
        setResumeData(prev => ({
            ...prev,
            projects: prev.projects.filter((_, i) => i !== index)
        }));
    };

    const addSkill = () => {
        if (customSkill && !resumeData.skills.includes(customSkill)) {
            setResumeData(prev => ({
                ...prev,
                skills: [...prev.skills, customSkill]
            }));
            setCustomSkill('');
        }
    };

    const removeSkill = (skill) => {
        setResumeData(prev => ({
            ...prev,
            skills: prev.skills.filter(s => s !== skill)
        }));
    };

    const generateResume = () => {
        const resumeText = `
RESUME

${resumeData.personalInfo.fullName.toUpperCase()}
${resumeData.personalInfo.email} | ${resumeData.personalInfo.phone}
${resumeData.personalInfo.location}
${resumeData.personalInfo.linkedIn ? `LinkedIn: ${resumeData.personalInfo.linkedIn}` : ''}
${resumeData.personalInfo.portfolio ? `Portfolio: ${resumeData.personalInfo.portfolio}` : ''}

SUMMARY
${resumeData.personalInfo.summary}

EDUCATION
${resumeData.education.map(edu => 
    `${edu.degree} - ${edu.institution} (${edu.year})${edu.gpa ? ` | GPA: ${edu.gpa}` : ''}`
).join('\n')}

EXPERIENCE
${resumeData.experience.map(exp => 
    `${exp.title} at ${exp.company}
${exp.location} | ${exp.startDate} - ${exp.endDate}
${exp.description}`
).join('\n\n')}

SKILLS
${resumeData.skills.join(', ')}

PROJECTS
${resumeData.projects.map(proj => 
    `${proj.name}
${proj.description}
Technologies: ${proj.technologies}
${proj.link ? `Link: ${proj.link}` : ''}`
).join('\n\n')}
        `.trim();

        const blob = new Blob([resumeText], { type: 'text/plain' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${resumeData.personalInfo.fullName || 'resume'}_resume.txt`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
        
        toast.success('Resume downloaded successfully!');
    };

    const saveResume = async () => {
        try {
            const resumeLink = await generateResumeLink();
            toast.success('Resume saved! You can use this link in job applications.');
            return resumeLink;
        } catch (error) {
            toast.error('Failed to save resume');
        }
    };

    const generateResumeLink = async () => {
        const resumeText = JSON.stringify(resumeData);
        return `data:text/json;charset=utf-8,${encodeURIComponent(resumeText)}`;
    };

    return (
        <>
            <ToastContainer position="top-right" />
            <div className="min-h-screen bg-mine-shaft-950">
                <Header />
                <div className="container mx-auto px-4 py-8">
                    <div className="flex justify-between items-center mb-6">
                        <h1 className="text-3xl font-bold text-mine-shaft-100">Build Your Resume</h1>
                        <button
                            onClick={() => navigate(-1)}
                            className="bg-none border border-cyan-500 hover:bg-cyan-500 text-mine-shaft-100 rounded-md px-6 py-2 hover:text-mine-shaft-900"
                        >
                            Back
                        </button>
                    </div>

                    <div className="bg-mine-shaft-800 rounded-lg p-6 mb-6">
                        <h2 className="text-2xl font-semibold text-mine-shaft-100 mb-4 flex items-center">
                            <FaUser className="mr-2 text-cyan-500" /> Personal Information
                        </h2>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Full Name</label>
                                <input
                                    type="text"
                                    name="fullName"
                                    value={resumeData.personalInfo.fullName}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="John Doe"
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Email</label>
                                <input
                                    type="email"
                                    name="email"
                                    value={resumeData.personalInfo.email}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="john@example.com"
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Phone</label>
                                <input
                                    type="tel"
                                    name="phone"
                                    value={resumeData.personalInfo.phone}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="+1 234 567 8900"
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Location</label>
                                <input
                                    type="text"
                                    name="location"
                                    value={resumeData.personalInfo.location}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="City, Country"
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">LinkedIn</label>
                                <input
                                    type="url"
                                    name="linkedIn"
                                    value={resumeData.personalInfo.linkedIn}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="https://linkedin.com/in/yourprofile"
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Portfolio</label>
                                <input
                                    type="url"
                                    name="portfolio"
                                    value={resumeData.personalInfo.portfolio}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    placeholder="https://yourportfolio.com"
                                />
                            </div>
                            <div className="md:col-span-2">
                                <label className="block text-sm font-medium text-mine-shaft-300 mb-2">Professional Summary</label>
                                <textarea
                                    name="summary"
                                    value={resumeData.personalInfo.summary}
                                    onChange={handlePersonalInfoChange}
                                    className="w-full bg-mine-shaft-700 text-mine-shaft-100 p-3 rounded-md"
                                    rows="4"
                                    placeholder="Brief summary of your professional background..."
                                />
                            </div>
                        </div>
                    </div>

                    <div className="bg-mine-shaft-800 rounded-lg p-6 mb-6">
                        <div className="flex justify-between items-center mb-4">
                            <h2 className="text-2xl font-semibold text-mine-shaft-100 flex items-center">
                                <FaGraduationCap className="mr-2 text-cyan-500" /> Education
                            </h2>
                            <button
                                onClick={addEducation}
                                className="bg-cyan-500 text-mine-shaft-900 px-4 py-2 rounded-md hover:bg-cyan-600"
                            >
                                Add Education
                            </button>
                        </div>
                        {resumeData.education.map((edu, index) => (
                            <div key={index} className="bg-mine-shaft-700 p-4 rounded-md mb-4">
                                <div className="flex justify-between items-center mb-2">
                                    <h3 className="text-lg font-semibold text-mine-shaft-100">Education {index + 1}</h3>
                                    {resumeData.education.length > 1 && (
                                        <button
                                            onClick={() => removeEducation(index)}
                                            className="text-red-500 hover:text-red-600"
                                        >
                                            Remove
                                        </button>
                                    )}
                                </div>
                                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                    <input
                                        type="text"
                                        name="degree"
                                        value={edu.degree}
                                        onChange={(e) => handleEducationChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Degree"
                                    />
                                    <input
                                        type="text"
                                        name="institution"
                                        value={edu.institution}
                                        onChange={(e) => handleEducationChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Institution"
                                    />
                                    <input
                                        type="text"
                                        name="year"
                                        value={edu.year}
                                        onChange={(e) => handleEducationChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Year"
                                    />
                                    <input
                                        type="text"
                                        name="gpa"
                                        value={edu.gpa}
                                        onChange={(e) => handleEducationChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="GPA (optional)"
                                    />
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="bg-mine-shaft-800 rounded-lg p-6 mb-6">
                        <div className="flex justify-between items-center mb-4">
                            <h2 className="text-2xl font-semibold text-mine-shaft-100 flex items-center">
                                <FaBriefcase className="mr-2 text-cyan-500" /> Experience
                            </h2>
                            <button
                                onClick={addExperience}
                                className="bg-cyan-500 text-mine-shaft-900 px-4 py-2 rounded-md hover:bg-cyan-600"
                            >
                                Add Experience
                            </button>
                        </div>
                        {resumeData.experience.map((exp, index) => (
                            <div key={index} className="bg-mine-shaft-700 p-4 rounded-md mb-4">
                                <div className="flex justify-between items-center mb-2">
                                    <h3 className="text-lg font-semibold text-mine-shaft-100">Experience {index + 1}</h3>
                                    {resumeData.experience.length > 1 && (
                                        <button
                                            onClick={() => removeExperience(index)}
                                            className="text-red-500 hover:text-red-600"
                                        >
                                            Remove
                                        </button>
                                    )}
                                </div>
                                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                    <input
                                        type="text"
                                        name="title"
                                        value={exp.title}
                                        onChange={(e) => handleExperienceChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Job Title"
                                    />
                                    <input
                                        type="text"
                                        name="company"
                                        value={exp.company}
                                        onChange={(e) => handleExperienceChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Company"
                                    />
                                    <input
                                        type="text"
                                        name="location"
                                        value={exp.location}
                                        onChange={(e) => handleExperienceChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Location"
                                    />
                                    <div className="grid grid-cols-2 gap-2">
                                        <input
                                            type="text"
                                            name="startDate"
                                            value={exp.startDate}
                                            onChange={(e) => handleExperienceChange(index, e)}
                                            className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                            placeholder="Start Date"
                                        />
                                        <input
                                            type="text"
                                            name="endDate"
                                            value={exp.endDate}
                                            onChange={(e) => handleExperienceChange(index, e)}
                                            className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                            placeholder="End Date"
                                        />
                                    </div>
                                    <div className="md:col-span-2">
                                        <textarea
                                            name="description"
                                            value={exp.description}
                                            onChange={(e) => handleExperienceChange(index, e)}
                                            className="w-full bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                            rows="3"
                                            placeholder="Job description and achievements..."
                                        />
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="bg-mine-shaft-800 rounded-lg p-6 mb-6">
                        <h2 className="text-2xl font-semibold text-mine-shaft-100 mb-4 flex items-center">
                            <FaCode className="mr-2 text-cyan-500" /> Skills
                        </h2>
                        <div className="flex gap-2 mb-4">
                            <input
                                type="text"
                                value={customSkill}
                                onChange={(e) => setCustomSkill(e.target.value)}
                                onKeyPress={(e) => e.key === 'Enter' && addSkill()}
                                className="flex-1 bg-mine-shaft-700 text-mine-shaft-100 p-2 rounded-md"
                                placeholder="Add a skill"
                            />
                            <button
                                onClick={addSkill}
                                className="bg-cyan-500 text-mine-shaft-900 px-4 py-2 rounded-md hover:bg-cyan-600"
                            >
                                Add
                            </button>
                        </div>
                        <div className="flex flex-wrap gap-2">
                            {resumeData.skills.map((skill, index) => (
                                <div
                                    key={index}
                                    className="bg-mine-shaft-700 text-mine-shaft-100 px-4 py-2 rounded-full flex items-center gap-2"
                                >
                                    <span>{skill}</span>
                                    <button
                                        onClick={() => removeSkill(skill)}
                                        className="text-red-500 hover:text-red-600"
                                    >
                                        ×
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div className="bg-mine-shaft-800 rounded-lg p-6 mb-6">
                        <div className="flex justify-between items-center mb-4">
                            <h2 className="text-2xl font-semibold text-mine-shaft-100 flex items-center">
                                <FaFileAlt className="mr-2 text-cyan-500" /> Projects
                            </h2>
                            <button
                                onClick={addProject}
                                className="bg-cyan-500 text-mine-shaft-900 px-4 py-2 rounded-md hover:bg-cyan-600"
                            >
                                Add Project
                            </button>
                        </div>
                        {resumeData.projects.map((proj, index) => (
                            <div key={index} className="bg-mine-shaft-700 p-4 rounded-md mb-4">
                                <div className="flex justify-between items-center mb-2">
                                    <h3 className="text-lg font-semibold text-mine-shaft-100">Project {index + 1}</h3>
                                    {resumeData.projects.length > 1 && (
                                        <button
                                            onClick={() => removeProject(index)}
                                            className="text-red-500 hover:text-red-600"
                                        >
                                            Remove
                                        </button>
                                    )}
                                </div>
                                <div className="grid grid-cols-1 gap-4">
                                    <input
                                        type="text"
                                        name="name"
                                        value={proj.name}
                                        onChange={(e) => handleProjectChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Project Name"
                                    />
                                    <textarea
                                        name="description"
                                        value={proj.description}
                                        onChange={(e) => handleProjectChange(index, e)}
                                        className="w-full bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        rows="3"
                                        placeholder="Project description..."
                                    />
                                    <input
                                        type="text"
                                        name="technologies"
                                        value={proj.technologies}
                                        onChange={(e) => handleProjectChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Technologies used (comma separated)"
                                    />
                                    <input
                                        type="url"
                                        name="link"
                                        value={proj.link}
                                        onChange={(e) => handleProjectChange(index, e)}
                                        className="bg-mine-shaft-600 text-mine-shaft-100 p-2 rounded-md"
                                        placeholder="Project link (optional)"
                                    />
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="flex gap-4 justify-end">
                        <button
                            onClick={generateResume}
                            className="bg-cyan-500 text-mine-shaft-900 px-6 py-3 rounded-md font-semibold hover:bg-cyan-600"
                        >
                            Download Resume
                        </button>
                        <button
                            onClick={saveResume}
                            className="bg-mine-shaft-700 text-mine-shaft-100 px-6 py-3 rounded-md font-semibold hover:bg-mine-shaft-600"
                        >
                            Save Resume
                        </button>
                    </div>
                </div>
                <Footer />
            </div>
        </>
    );
};

export default ResumeBuilder;

